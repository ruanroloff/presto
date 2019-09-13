/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.prestosql.elasticsearch;

import io.airlift.configuration.Config;
import io.airlift.configuration.ConfigDescription;
import io.airlift.configuration.ConfigSecuritySensitive;
import io.airlift.configuration.DefunctConfig;
import io.airlift.units.Duration;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import java.io.File;
import java.util.Optional;

import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

@DefunctConfig({
        "elasticsearch.max-hits",
        "elasticsearch.cluster-name",
        "searchguard.ssl.certificate-format",
        "searchguard.ssl.pemcert-filepath",
        "searchguard.ssl.pemkey-filepath",
        "searchguard.ssl.pemkey-password",
        "searchguard.ssl.pemtrustedcas-filepath",
        "searchguard.ssl.keystore-filepath",
        "searchguard.ssl.keystore-password",
        "searchguard.ssl.truststore-filepath",
        "searchguard.ssl.truststore-password"})
public class ElasticsearchConfig
{
    private String host;
    private int port = 9200;
    private String defaultSchema = "default";
    private File tableDescriptionDirectory = new File("etc/elasticsearch/");
    private int scrollSize = 1_000;
    private Duration scrollTimeout = new Duration(1, SECONDS);
    private Duration requestTimeout = new Duration(100, MILLISECONDS);
    private Duration connectTimeout = new Duration(1, SECONDS);
    private int maxRequestRetries = 5;
    private Duration maxRetryTime = new Duration(10, SECONDS);

    private boolean tlsEnabled;
    private File keystorePath;
    private File trustStorePath;
    private String keystorePassword;
    private String truststorePassword;
    private boolean verifyHostnames = true;

    @NotNull
    public String getHost()
    {
        return host;
    }

    @Config("elasticsearch.host")
    public ElasticsearchConfig setHost(String host)
    {
        this.host = host;
        return this;
    }

    public int getPort()
    {
        return port;
    }

    @Config("elasticsearch.port")
    public ElasticsearchConfig setPort(int port)
    {
        this.port = port;
        return this;
    }

    @NotNull
    public File getTableDescriptionDirectory()
    {
        return tableDescriptionDirectory;
    }

    @Config("elasticsearch.table-description-directory")
    @ConfigDescription("Directory that contains JSON table description files")
    public ElasticsearchConfig setTableDescriptionDirectory(File tableDescriptionDirectory)
    {
        this.tableDescriptionDirectory = tableDescriptionDirectory;
        return this;
    }

    @NotNull
    public String getDefaultSchema()
    {
        return defaultSchema;
    }

    @Config("elasticsearch.default-schema-name")
    @ConfigDescription("Default schema name to use")
    public ElasticsearchConfig setDefaultSchema(String defaultSchema)
    {
        this.defaultSchema = defaultSchema;
        return this;
    }

    @NotNull
    @Min(1)
    public int getScrollSize()
    {
        return scrollSize;
    }

    @Config("elasticsearch.scroll-size")
    @ConfigDescription("Scroll batch size")
    public ElasticsearchConfig setScrollSize(int scrollSize)
    {
        this.scrollSize = scrollSize;
        return this;
    }

    @NotNull
    public Duration getScrollTimeout()
    {
        return scrollTimeout;
    }

    @Config("elasticsearch.scroll-timeout")
    @ConfigDescription("Scroll timeout")
    public ElasticsearchConfig setScrollTimeout(Duration scrollTimeout)
    {
        this.scrollTimeout = scrollTimeout;
        return this;
    }

    @NotNull
    public Duration getRequestTimeout()
    {
        return requestTimeout;
    }

    @Config("elasticsearch.request-timeout")
    @ConfigDescription("Elasticsearch request timeout")
    public ElasticsearchConfig setRequestTimeout(Duration requestTimeout)
    {
        this.requestTimeout = requestTimeout;
        return this;
    }

    @NotNull
    public Duration getConnectTimeout()
    {
        return connectTimeout;
    }

    @Config("elasticsearch.connect-timeout")
    @ConfigDescription("Elasticsearch connect timeout")
    public ElasticsearchConfig setConnectTimeout(Duration timeout)
    {
        this.connectTimeout = timeout;
        return this;
    }

    @Min(1)
    public int getMaxRequestRetries()
    {
        return maxRequestRetries;
    }

    @Config("elasticsearch.max-request-retries")
    @ConfigDescription("Maximum number of Elasticsearch request retries")
    public ElasticsearchConfig setMaxRequestRetries(int maxRequestRetries)
    {
        this.maxRequestRetries = maxRequestRetries;
        return this;
    }

    @NotNull
    public Duration getMaxRetryTime()
    {
        return maxRetryTime;
    }

    @Config("elasticsearch.max-request-retry-time")
    @ConfigDescription("Use exponential backoff starting at 1s up to the value specified by this configuration when retrying failed requests")
    public ElasticsearchConfig setMaxRetryTime(Duration maxRetryTime)
    {
        this.maxRetryTime = maxRetryTime;
        return this;
    }

    public boolean isTlsEnabled()
    {
        return tlsEnabled;
    }

    @Config("elasticsearch.tls.enabled")
    public ElasticsearchConfig setTlsEnabled(boolean tlsEnabled)
    {
        this.tlsEnabled = tlsEnabled;
        return this;
    }

    public Optional<File> getKeystorePath()
    {
        return Optional.ofNullable(keystorePath);
    }

    @Config("elasticsearch.tls.keystore-path")
    public ElasticsearchConfig setKeystorePath(File path)
    {
        this.keystorePath = path;
        return this;
    }

    public Optional<String> getKeystorePassword()
    {
        return Optional.ofNullable(keystorePassword);
    }

    @Config("elasticsearch.tls.keystore-password")
    @ConfigSecuritySensitive
    public ElasticsearchConfig setKeystorePassword(String password)
    {
        this.keystorePassword = password;
        return this;
    }

    public Optional<File> getTrustStorePath()
    {
        return Optional.ofNullable(trustStorePath);
    }

    @Config("elasticsearch.tls.truststore-path")
    public ElasticsearchConfig setTrustStorePath(File path)
    {
        this.trustStorePath = path;
        return this;
    }

    public Optional<String> getTruststorePassword()
    {
        return Optional.ofNullable(truststorePassword);
    }

    @Config("elasticsearch.tls.truststore-password")
    @ConfigSecuritySensitive
    public ElasticsearchConfig setTruststorePassword(String password)
    {
        this.truststorePassword = password;
        return this;
    }

    public boolean isVerifyHostnames()
    {
        return verifyHostnames;
    }

    @Config("elasticsearch.tls.verify-hostnames")
    public ElasticsearchConfig setVerifyHostnames(boolean verify)
    {
        this.verifyHostnames = verify;
        return this;
    }
}